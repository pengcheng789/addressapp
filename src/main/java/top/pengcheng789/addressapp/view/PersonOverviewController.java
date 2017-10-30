package top.pengcheng789.addressapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import top.pengcheng789.addressapp.MainApp;
import top.pengcheng789.addressapp.model.Person;
import top.pengcheng789.addressapp.util.DateUtil;

/**
 * @author pen
 */
public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private MainApp mainApp;

    @FXML
    public void initialize() {
        // 初始化个人的姓名。
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        int[][] ints = {};
        ints['a'][1] = 1;

        // 清空人员详情。
        showPersonDetail(null);

        // 监听personTable的选择并改变人员详情。
        personTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPersonDetail(newValue)));
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("操作错误");
            alert.setHeaderText(null);
            alert.setContentText("请在列表中选择一位联系人！");
            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // 将ObservableList<Person>里的数据添加至表格里。
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * 在人员详情里填充信息，如果指定的人员为null，所有详情将会被清空。
     */
    private void showPersonDetail(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
}
