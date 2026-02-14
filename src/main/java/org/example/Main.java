package org.example;

import com.google.adk.agents.LlmAgent;
import com.google.adk.agents.RunConfig;
import com.google.adk.events.Event;
import com.google.adk.runner.InMemoryRunner;
import com.google.adk.sessions.Session;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import io.reactivex.rxjava3.core.Flowable;
import org.example.pages.DashBoard;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    public static void main(String[] args) {
        // 1) Start WebDriver (use WebDriverManager if you prefer)
        WebDriver driver = new ChromeDriver();

        // 2) Page Objects (methods annotated with @Tool)
        LoginPage loginPage = new LoginPage(driver);
        DashBoard dashboard = new DashBoard(driver);

        // 3) Build your agent and register tools by passing instances
        LlmAgent agent = LlmAgent.builder()
                .name("web_bot")
                .model("gemini-2.0-flash")
                .instruction("You are an autonomous web tester. Use the Page Objects to navigate and act.")
                .tools(
                        loginPage,
                        dashboard
                )
                .build();

        // 4) Runner + session (matches the official doc flow)
        RunConfig runConfig = RunConfig.builder().build();

        // If your InMemoryRunner needs a name, replace with new InMemoryRunner("web_bot", agent)
        InMemoryRunner runner = new InMemoryRunner(agent);

        Session session = runner
                .sessionService()
                .createSession(runner.appName(), "user1234")
                .blockingGet();

        System.out.println("CLI mode ready. Type 'quit' to exit.\n");

        try (Scanner scanner = new Scanner(System.in, UTF_8)) {
            while (true) {
                System.out.print("\nYou > ");
                String userInput = scanner.nextLine();
                if ("quit".equalsIgnoreCase(userInput)) {
                    break;
                }

                // 5) Create a user Content and run asynchronously
                Content userMsg = Content.fromParts(Part.fromText(userInput));
                Flowable<Event> events =
                        runner.runAsync(session.userId(), session.id(), userMsg, runConfig);

                System.out.print("\nAgent > ");
                events.blockingForEach(ev -> {
                    // Print only when final response is ready (official example behavior)
                    if (ev.finalResponse()) {
                        System.out.println(ev.stringifyContent());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try { driver.quit(); } catch (Exception ignore) {}
        }

        System.out.println("Exiting CLI. Bye!");
    }
}