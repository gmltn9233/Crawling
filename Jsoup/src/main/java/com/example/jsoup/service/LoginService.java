package com.example.jsoup.service;

import com.example.jsoup.dto.LoginDTO;
import com.example.jsoup.dto.UserDTO;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final String URL = "https://portal.sejong.ac.kr/jsp/login/loginSSL.jsp?rtUrl=portal.sejong.ac.kr/comm/member/user/ssoLoginProc.do";

    public UserDTO crawling() {
        WebDriver driver = initCrawling();
        UserDTO user = new UserDTO();
        try {
            driver.get(URL);
            Alert alert1 = driver.switchTo().alert();
            alert1.dismiss();
            Alert alert2 = driver.switchTo().alert();
            alert2.dismiss();
            WebElement username = driver.findElement(By.id("id"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginBtn"));

            username.sendKeys("");
            password.sendKeys("");

            System.out.println("로그인 성공!");
            loginButton.click();

            WebElement studentIdElement = driver.findElement(
                By.cssSelector(".main_wrap .my_info .box01 .text01 .txt2"));
            WebElement majorElement = driver.findElement(
                By.cssSelector(".main_wrap .my_info .box01 .text01 .txt4"));

            String studentId = studentIdElement.getText();
            String major = majorElement.getText();

            user.setStudentID(studentId);
            user.setMajor(major);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return user;
    }

    private WebDriver initCrawling() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Headless 모드 설정
        options.addArguments("--window-size=1920,1080"); // 화면 크기 설정
        options.addArguments("--disable-gpu"); // GPU 사용 안 함
        options.addArguments("--no-sandbox"); // 리눅스 환경에서 권한 문제 해결
        options.addArguments("--disable-dev-shm-usage"); // 메모리 문제 해결

        return new ChromeDriver(options);
    }

}
