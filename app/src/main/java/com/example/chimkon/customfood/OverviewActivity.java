package com.example.chimkon.customfood;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static com.example.chimkon.customfood.VeggieActivity.pizzaOrder;

public class OverviewActivity extends Activity {

    Button button;
    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
//    private static final String STRINGPASSED = "dough";
//    private String pizzaOrder;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set up mail
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_overview);

        listView = (ExpandableListView) findViewById(R.id.expListView);
        pizzaData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listView.setAdapter(listAdapter);
        button = (Button) findViewById(R.id.sendemailbutton);

    }

    // Send email
    public void threadEmail(View view) {
        new sendEmail().execute();
    }

    public void initiateEmail() {
        String destination = "catkutatca12@gmail.com";
        final String sender = "chimkontran@gmail.com";
        final String password = "amazingcherry";
        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            message.setSubject("Testing");
            Multipart multipart = new MimeMultipart();

            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(listDataChild.toString());

            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            Transport.send(message);
//            Transport transport = session.getTransport();
//            System.out.println("Sending");
//            transport.connect(host, 587, sender, password);
//            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//            System.out.println("Sent");
//            transport.close();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    // contain Pizza order details
    public void pizzaData() {
        TextView pizza;
        TextView pizzaID;
        ArrayList<String[]> pizzaOrders = pizzaOrder;
//        LinearLayout layout = (LinearLayout) findViewById(R.id.linearOverview);

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout layout = new LinearLayout(this);

        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(layoutParams);

        for (int i = 0; i < pizzaOrders.size(); i++) {
            listDataHeader.add("Pizza: " + (i + 1));
            List<String> newPizza = null;
            for (int j = 0; j < pizzaOrders.get(i).length; j++) {
                newPizza = new ArrayList<>();
                newPizza.add("Dough: " + pizzaOrders.get(i)[0]);
                newPizza.add("Sauce: " + pizzaOrders.get(i)[1]);
                newPizza.add("Cheese: " + pizzaOrders.get(i)[2]);
                newPizza.add("Meat: " + pizzaOrders.get(i)[3]);
                newPizza.add("Veggie: " + pizzaOrders.get(i)[4]);
                listDataChild.put(listDataHeader.get(i), newPizza);


            }
        }
    }

    // send email procedure: before, during, after send
    private class sendEmail extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            System.out.println("Do this first");
        }

        @Override
        protected String doInBackground(Void... params) {
            initiateEmail();
            return "";
        }

        @Override
        protected void onPostExecute(String emailResult) {
            button.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        }
    }
}
