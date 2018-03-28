package com.cesarbassani.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.cesarbassani.festafimdeano.R;
import com.cesarbassani.festafimdeano.constants.FimDeAnoConstantes;
import com.cesarbassani.festafimdeano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);

        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstantes.PRESENCE);

            if (presence.equals(FimDeAnoConstantes.CONFIRMED_WILL_GO)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.check_participate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstantes.PRESENCE, FimDeAnoConstantes.CONFIRMED_WILL_GO);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstantes.PRESENCE, FimDeAnoConstantes.CONFIRMED_WONT_GO);
            }
        }
    }

    private static class ViewHolder {

        CheckBox checkParticipate;
    }
}
