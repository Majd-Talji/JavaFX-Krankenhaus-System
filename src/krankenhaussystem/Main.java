package krankenhaussystem;

import com.majdtalji.krankenhaus.db.dao.Dao;
import com.majdtalji.krankenhaus.view.Login;
import com.majdtalji.krankenhaus.view.MessageView;
import com.majdtalji.krankenhaus.view.PatientInfoView;
import com.majdtalji.krankenhaus.view.Uber;
import com.majdtalji.krankenhaus.view.UsersView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        Login login = new Login();
        login.setVisible(true);
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
