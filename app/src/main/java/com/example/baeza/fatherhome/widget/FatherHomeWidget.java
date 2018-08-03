package com.example.baeza.fatherhome.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.baeza.fatherhome.R;

import java.util.Random;

public class FatherHomeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        String [] widget_array_text = context.getResources().getStringArray(R.array.widget_text_array);
        String [] widget_array_text_address = context.getResources().getStringArray(R.array.widget_text_array_address);

        String description = widget_array_text[new Random().nextInt(widget_array_text.length)];
        String address = widget_array_text_address[new Random().nextInt(widget_array_text_address.length)];

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.father_home_widget);
        views.setTextViewText(R.id.appwidget_text, description);
        views.setTextViewText(R.id.textViewAddress, address);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        //refresh the widget each 30 min with a new verse.
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            String [] widget_array_text = context.getResources().getStringArray(R.array.widget_text_array);
            String [] widget_array_text_address = context.getResources().getStringArray(R.array.widget_text_array_address);

            String description = widget_array_text[new Random().nextInt(widget_array_text.length)];
            String address = widget_array_text_address[new Random().nextInt(widget_array_text_address.length)];

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.father_home_widget);
            views.setTextViewText(R.id.appwidget_text, description);
            views.setTextViewText(R.id.textViewAddress, address);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

