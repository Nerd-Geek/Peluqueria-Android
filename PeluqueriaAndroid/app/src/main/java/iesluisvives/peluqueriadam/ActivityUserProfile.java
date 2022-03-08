package iesluisvives.peluqueriadam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActivityUserProfile extends AppCompatActivity {
    private ImageView userProfileImageView;
    private TextView  userProfileTextUsernameLabel,userProfileTextNameValue,userProfileTextSurnameValue,
            userProfileTextGenderValue,userProfileTextTelephoneValue,userProfileTextEmailValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initcomponents();
    }

    private void initcomponents() {
        userProfileImageView = findViewById(R.id.userProfileImageView);
        userProfileTextUsernameLabel = findViewById(R.id.userProfileTextUsernameLabel);
        userProfileTextNameValue = findViewById(R.id.userProfileTextNameValue);
        userProfileTextSurnameValue = findViewById(R.id.userProfileTextSurnameValue);
        userProfileTextGenderValue = findViewById(R.id.userProfileTextGenderValue);
        userProfileTextTelephoneValue = findViewById(R.id.userProfileTextTelephoneValue);
        userProfileTextEmailValue = findViewById(R.id.userProfileTextEmailValue);
        userProfileTextUsernameLabel.setText(LocalUser.getInstance().getUsername());
        userProfileTextNameValue.setText(LocalUser.getInstance().getName());
        userProfileTextSurnameValue.setText(LocalUser.getInstance().getSurname());
        userProfileTextGenderValue.setText(LocalUser.getInstance().getGender());
        userProfileTextTelephoneValue.setText(LocalUser.getInstance().getTelephone());
        userProfileTextEmailValue.setText(LocalUser.getInstance().getEmail());
        if(LocalUser.getInstance().getImage()!=null){
            Glide.with(getApplicationContext())
                    .load(LocalUser.getInstance().getImage().toString())
                    .into(userProfileImageView);
        }
    }
}