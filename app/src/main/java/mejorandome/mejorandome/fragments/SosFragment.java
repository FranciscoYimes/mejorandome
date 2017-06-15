package mejorandome.mejorandome.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mejorandome.mejorandome.PostSOSActivity;
import mejorandome.mejorandome.R;

public class SosFragment extends Fragment {

    private ImageView sosButton;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_sos, container, false);

        sosButton = (ImageView) rootView.findViewById(R.id.sos_button);

        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SosFragment.this.getActivity(), PostSOSActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}
