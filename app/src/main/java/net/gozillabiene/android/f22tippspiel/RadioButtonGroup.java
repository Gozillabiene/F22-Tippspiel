package net.gozillabiene.android.f22tippspiel;

import java.util.HashMap;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;


public class RadioButtonGroup implements OnCheckedChangeListener {


    private HashMap<Integer, RadioButton> mapRadioButtons;

    private int checkedRadioButton = 0;

    private boolean isButtonChecked = false;


    public RadioButtonGroup() {
        mapRadioButtons = new HashMap<Integer, RadioButton>();
    }

    public boolean addRadioButton(RadioButton rButton) {
        return addRadioButton(rButton, false);
    }

    public boolean addRadioButton(RadioButton rButton, boolean checked) {
        if (rButton != null) {

            rButton.setOnCheckedChangeListener(this);
            if (checked && !isButtonChecked) {

                rButton.setChecked(true);
                isButtonChecked = true;
            }

            mapRadioButtons.put(rButton.getId(), rButton);
            return true;
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        RadioButton radio = (RadioButton)buttonView;
        if (radio != null) {
            if (checkedRadioButton == 0) {
                // nothing to uncheck, only store the checked button
                checkedRadioButton = radio.getId();
            }else{
                // unchecked the old checked button
                RadioButton uncheckRadio = mapRadioButtons.get(checkedRadioButton);
                uncheckRadio.setChecked(false);
                // store the new checked button
                checkedRadioButton = radio.getId();
            }
        }
    }

    public RadioButton getCheckedRadioButton() {
        if (checkedRadioButton == 0) {
            return null;
        }else{
            return mapRadioButtons.get(checkedRadioButton);
        }
    }
}