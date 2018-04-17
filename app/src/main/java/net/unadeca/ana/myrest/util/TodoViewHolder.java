package net.unadeca.ana.myrest.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import net.unadeca.ana.myrest.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by ANA on 19/02/2018.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {
    public HtmlTextView html;

    public TodoViewHolder(View itemView) {
        super(itemView);
        html= itemView.findViewById(R.id.html_text);
    }
}
