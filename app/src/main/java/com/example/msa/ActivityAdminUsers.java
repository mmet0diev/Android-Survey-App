package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class ActivityAdminUsers extends AppCompatActivity {
    private DBHelper dbHelper;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        dbHelper = new DBHelper(this);
        users = dbHelper.get_userList();

        ListView listView = findViewById(R.id.users_listview);
        UserAdapter adapter = new UserAdapter(this, users);
        listView.setAdapter(adapter);
    }

    public class UserAdapter extends BaseAdapter {

        private ArrayList<User> users;
        private LayoutInflater inflater;

        public UserAdapter(Context context, ArrayList<User> users){
            this.users = users;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null){
                view = inflater.inflate(R.layout.activity_admin_users_view, parent, false);
            }

            User user = users.get(position);

            TextView userText = view.findViewById(R.id.usertxt);
            userText.setText(user.toString());

            return view;
        }
    }
}