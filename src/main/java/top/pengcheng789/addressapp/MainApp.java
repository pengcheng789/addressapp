package top.pengcheng789.addressapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import top.pengcheng789.addressapp.model.Person;
import top.pengcheng789.addressapp.view.PersonOverviewController;

import java.io.IOException;

/**
 * @author pen
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp() {
        // 添加一些数据。
        personData.add(new Person("健林", "王"));
        personData.add(new Person("嘉诚", "李"));
        personData.add(new Person("云", "马"));
        personData.add(new Person("化腾", "马"));
        personData.add(new Person("兆基", "李"));
        personData.add(new Person("磊", "丁"));
        personData.add(new Person("卫", "王"));
        personData.add(new Person("炳江", "郭"));
        personData.add(new Person("銮雄", "刘"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("地址簿");
        primaryStage.setResizable(false);

        initRootLayout();

        showPersonOverview();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 初始化根布局
     */
    public void initRootLayout() {
        try {
            // 加载跟布局的fxml文件。
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = loader.load();

            // 在Scene中展示根布局。
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在根布局中展示个人详情。
     */
    public void showPersonOverview() {
        try {
            // 加载个人详情的fxml文件。
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // 将个人详情面板设置为根布局的center。
            rootLayout.setCenter(personOverview);

            // 让PersonOverviewController能够访问这个MainApp。
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }
}
