package com.abc.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RouteFilter extends ZuulFilter {

    // 定义一个令牌桶，每秒产生2个令牌，即每秒最多处理2个请求
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("通过过滤");
        return null;
    }

    @Override
    public boolean shouldFilter() {
        // 获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();

        // tryAcquire() 只要能够马上获致到1个令牌，则返回true，不阻塞
        // tryAcquire(5, 3, TimeUnit.SECONDS) 在3秒钟内可以获取到5个令牌，则返回true，不阻塞
        // acquire(5) 获取到5个令牌，否则一直等待，会阻塞，返回值为阻塞的时长
        // acquire() 获取到1个令牌，否则一直等待，会阻塞，返回值为阻塞的时长
        if(!RATE_LIMITER.tryAcquire()) {
            log.warn("访问量超载");
            // 指定当前请求未通过过滤
            context.setSendZuulResponse(false);
            // 向客户端返回响应码429，请求数量过多
            context.setResponseStatusCode(429);
            return false;
        }
        return true;
    }
}
