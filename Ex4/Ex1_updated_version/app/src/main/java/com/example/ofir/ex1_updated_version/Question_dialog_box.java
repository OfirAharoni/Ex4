package com.example.ofir.ex1_updated_version;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Question_dialog_box extends DialogFragment
{

    private final String MSG_TAG = "msg";
    private final String NA_TAG = "N/A";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.delete_dialog_box, null);
        Button cancel_btn = view.findViewById(R.id.cancel_btn);
        Button del_btn = view.findViewById(R.id.del_btn);

        Bundle args = getArguments();
        final String selected_msg = args.getString(MSG_TAG, NA_TAG);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close the dialog with no further actions
                dismiss();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i < MainActivity.messages.size(); i++)
                {
                    String msg = MainActivity.messages.get(i).getContent();
                    if (msg.equals(selected_msg))
                    {
                        Message message_to_rm = MainActivity.messages.get(i);
                        DataBaseDelete dataBaseDelete = new DataBaseDelete();
                        dataBaseDelete.execute(message_to_rm.getContent(), message_to_rm.getId(),
                                message_to_rm.getTimestamp());
                        MainActivity.messages.remove(message_to_rm);
                        break;
                    }
                }

                // update adapter
                MainActivity.recyclerViewAdapter.notifyDataSetChanged();
                dismiss();
            }
        });

        return view;
    }
}
