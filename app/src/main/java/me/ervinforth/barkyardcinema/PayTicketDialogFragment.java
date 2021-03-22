package me.ervinforth.barkyardcinema;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

public class PayTicketDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = "PayTicketDialogFragment";
    private ImageButton closeBtn;
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView ticketPrice;
    private Spinner cinemaSpinner;
    private Spinner quantitySpinner;
    private Spinner startTimeSpinner;
    private Button payBtn;

    public PayTicketDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_fragment_pay_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        closeBtn = view.findViewById(R.id.pay_dialog_close_btn);
        moviePoster = view.findViewById(R.id.pay_dialog_poster);
        movieTitle = view.findViewById(R.id.pay_dialog_title);
        ticketPrice = view.findViewById(R.id.pay_dialog_price);
        cinemaSpinner = view.findViewById(R.id.pay_dialog_cinema);
        quantitySpinner = view.findViewById(R.id.pay_dialog_quantity);
        startTimeSpinner = view.findViewById(R.id.pay_dialog_start_time);
        payBtn = view.findViewById(R.id.pay_dialog_pay_btn);

        final float initialTicketPrice = 1500;

        ticketPrice.setText(String.format(Locale.ENGLISH,"JMD %,.2f", initialTicketPrice));

        SharedViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(SharedViewModel.class);

        viewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                movieTitle.setText(movie.getTitle());
                Glide.with(requireContext()).load(movie.getPoster()).into(moviePoster);
            }
        });

        String[] cinemas = new String[] {"Carib 5", "New Kingston Drive-In", "Sunshine Palace"};
        Integer[] quantities = new Integer[] {1,2,3,4,5,6,7,8,9,10};
        String[] startTimes = new String[] {"1:15 pm", "5:15 pm", "8:15 pm"};

        ArrayAdapter<CharSequence> cinemaAdapter = new ArrayAdapter<CharSequence>(getContext(),
                android.R.layout.simple_spinner_item, cinemas);
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_item, quantities);
        ArrayAdapter<CharSequence> statTimeAdapter = new ArrayAdapter<CharSequence>(getContext(),
                android.R.layout.simple_spinner_item, startTimes);

        cinemaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cinemaSpinner.setAdapter(cinemaAdapter);
        quantitySpinner.setAdapter(quantityAdapter);
        startTimeSpinner.setAdapter(statTimeAdapter);

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int quantity = (int)adapterView.getItemAtPosition(i);
                float lineTotal = quantity * initialTicketPrice;
                ticketPrice.setText(String.format(Locale.ENGLISH,"JMD %,.2f", lineTotal));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}