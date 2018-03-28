package com.cesarbassani.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cesarbassani.festafimdeano.R;
import com.cesarbassani.festafimdeano.constants.FimDeAnoConstantes;
import com.cesarbassani.festafimdeano.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder= new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.verifyPresence();
    }

    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstantes.PRESENCE);

        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if (presence.equals(FimDeAnoConstantes.CONFIRMED_WILL_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstantes.PRESENCE);

            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra(FimDeAnoConstantes.PRESENCE, presence);

            startActivity(intent);
        }

    }

    private static class ViewHolder {

        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
