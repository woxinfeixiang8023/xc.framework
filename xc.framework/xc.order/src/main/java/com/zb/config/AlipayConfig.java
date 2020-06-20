package com.zb.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016102400750983";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDGOsXuOBDajYdXw7GXa4ffnBF5OHam8OutgbwsHrDyxcTB0TU9VfukrtyYtx/YZ7uh+M2oGJ0fFX/JgVHxdXCdub5yriQ1xKGbCwcPeaaTnNfM3KF0u4aLDgC9s6unWf2TEdL0hx3fRPQd2CY22eTIvu402J1DvPRilyel1qmGhJguuEA+f4IsRj1nzOKL8g2qCEsSXmAyPmwhMiM72qISjx6YCAz8vLc88hrDoyjTTi2OqZoM2vqqOdkRuffj9V4ZNYnM/agBtCVWKb51iH48uDPvQi2PotJSPLOhQ2pq/EVGuMsdXToj5ZQN/u87VSc1sj+51I0vdSVI+BNbseH7AgMBAAECggEAeWugt5USBbzxLPoKetR76qT/MwA59XHW+nVgHKpD+sDXD10lPke/lxUgvE5XLEgF72WmGxPQkiyqFHSpC7zC2Wnf1QED3wdtcbmkfn8mYH6O1MZEA2umMuDF5Wz7dMuzffVKAUwOKSdkXC984NZAxBHneOlCEy7XhthNidBeEK1ryZZIAZwr5Kiqs2Wuf6a6moTEeWluUsxGuN5PjFDWrSnexiSH8ZA6rAFxpjxPq9fNC3OyyR9GG+lX2+dBUp/L7Ku2L/gzUjRUX1VB4rGd/LjUrueQ79uzUFmj+7xcZp/rGpZwKmqz3JDb3XPgyZ3hIj7HCAr+UxqyqZ0ShQ8q2QKBgQD6JMZ/tM7/S735YmyKeYuugu9VVHin5VLSCLjajEklg3AkTLAby+Q3G1J4Ae3JM+SceObDXAup4uYUx9D7+X1WwVHrBOlcsbfeN0+rUG8Gz70wFCa01E3YmYh8O5OrNqw7m7TSbFsyLY4+7tw1TPKIanDO6ETD3tvLnT+Ugfi21wKBgQDK3tpoYvDDxCTPmtLY6825sdTGYi/6TZRo1lED/9Xu4HPNH9/kClhumvSZ9u0uVrWSKUBfUSUhzzGcpx/6xm/ZgEJXeugCUQBs+kzTIyNy6JJk/R2GrQ5B8Wdp47XoCaQImy8QU4Z/Hlp+K3BcXjyyWH34qRd/TOgPJrTqw7/dfQKBgBOdXJ9Y+z5TnyL2D9LP8x8ND11ekVOBDPyBSJWCmJ3Db8C47rpPQ6b6kn3skMvNMOkAJgA9sIBy1V3dyUpo5uYtpJHMQcxN90AKkEm7tekP0yL5AYv+ddg9OdGcDllHoTvPWt+BNal6nwPaURQw43fE+PGQjwvY5t1RiFHJ20njAoGBAL3v/k7Da6XuS4Sv8P61b4yPaaY/oanY3r11Aq8a5YJcVS9Uy3snEHqly6MnJBNBjYgF5UYYpApOgaWod3GEPo5F3lcnNHeOvTZK9oirmnHoO8Afl3hTKEknKmpijxprhE+mc172xLLZiWIda3qfpHZaEb8xGVXPfGlLFRHEKivFAoGBAOr4AwAc3paEl4JmP2sZoanOsC5/NWIGqozVfkJP3AHNZJ/p67jo+llNsN14dRPPCBHq2aqPm/mWfEeFnx0epTkmiIJjptxpVvJLcxg7ZifBE+ydzYgBEzmvYiDj4E38zbjK0aMOXxloxQcHV5oFh0XxYCsTKlYE6fJC72KYVF/f";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8001/order/mynotfiy";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8001/order/myreturn";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj8q12kvYbMcRuyFrOSBVlvwBxl6yT6BC3lOP/kFNh1sPF4x7KNb5J9M05mJQhuevnJ+io7qm7xpyLnFWQZK+iyWv1slBkwyo2mI627be7YwolCK8wP0pof6ejKYVKe6QaDRPXDizyLbhFOQ7UIp+g5CVVpsJcDKY7CMY7jYrgnL64i8ZNFzXnNzNlvIIOXUDqTLlZoNdIx3IxJw43wMVAa8PC+d8+5NbyQkL3TnNYPVbwSh/9cJQR5V58B6hCpRIsQ1uXRBvNfJg/1vWNGwqN7lPUfI6eIQepZSndWsEr0800vrLVgFY211BXvBnlgcm1nUcg3x8wGoJUPLznqbaAwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
