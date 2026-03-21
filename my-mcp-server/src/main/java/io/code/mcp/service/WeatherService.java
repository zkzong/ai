package io.code.mcp.service;

/**
 * @Author: zongz
 * @Date: 2026-03-21
 */

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeatherService {

    @Tool(description = "获取指定城市的实时天气")
    public String getWeather(String cityName) {
        return mockWeatherData(cityName);
    }

    private String mockWeatherData(String city) {
        Map<String, String> data = Map.of(
                "北京", "晴 25℃ 西北风2级",
                "上海", "多云 31℃ 东南风2级",
                "广州", "雷阵雨 33℃ 南风3级");
        return data.getOrDefault(city, "天气数据暂不可用");
    }
}
