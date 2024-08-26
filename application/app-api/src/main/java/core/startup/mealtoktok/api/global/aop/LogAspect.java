package core.startup.mealtoktok.api.global.aop;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserCacheManager;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final UserCacheManager userCacheManager;

    @Pointcut(
            "execution(* core.startup.mealtoktok.domain..*(..)) && execution(* core.startup.mealtoktok.infra..*(..))&& !execution(* core.startup.mealtoktok.common..*(..))")
    public void all() {}

    @Pointcut(
            "execution(* core.startup.mealtoktok.api..*Api.*(..)) && !execution(* core.startup.mealtoktok.api.HealthCheckApi..*(..))")
    public void controller() {}

    @Around("all()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long timeinMs = end - start;
            log.info("{} | time = {}ms", joinPoint.getSignature(), timeinMs);
        }
    }

    @Around("controller()")
    public Object loggingBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes)
                                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();

        String controllerName = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, Object> params = new HashMap<>();

        try {
            String decodedURI = URLDecoder.decode(request.getRequestURI(), "UTF-8");

            params.put("controller", controllerName);
            params.put("method", methodName);
            params.put("params", getParams(request));
            params.put("log_time", System.currentTimeMillis());
            params.put("request_uri", decodedURI);
            params.put("http_method", request.getMethod());
        } catch (Exception e) {
            log.error("LoggerAspect error", e);
        }

        log.info("[{}] {}", params.get("http_method"), params.get("request_uri"));
        log.info("method: {}.{}", params.get("controller"), params.get("method"));
        log.info("params: {}", params.get("params"));

        Object result = joinPoint.proceed();

        return result;
    }

    @Around("execution(* core.startup.mealtoktok.domain.user..*Updater.*(..)) && args(user, ..)")
    public Object cacheUpdate(ProceedingJoinPoint joinPoint, User user) throws Throwable {
        Object result = joinPoint.proceed();
        userCacheManager.delete(user);
        return result;
    }

    private static JSONObject getParams(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }
}
