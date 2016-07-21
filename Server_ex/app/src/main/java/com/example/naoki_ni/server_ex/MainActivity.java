package com.example.naoki_ni.server_ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView mId, mTeam, mName, mPosition;
    TextView mId1, mTeam1, mName1, mPosition1;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mId = (TextView) findViewById(R.id.id);
        mTeam = (TextView) findViewById(R.id.team);
        mName = (TextView) findViewById(R.id.name);
        mPosition = (TextView) findViewById(R.id.position);

        mId1 = (TextView) findViewById(R.id.id1);
        mTeam1 = (TextView) findViewById(R.id.team1);
        mName1 = (TextView) findViewById(R.id.name1);
        mPosition1 = (TextView) findViewById(R.id.position1);

        String url = "http://ambient.naist.jp/ex/rei.php";
        //受け取るjsonの最初が配列[]の場合JSONObjectをJSONArrayに変更する
        JsonArrayRequest jsArrReuest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        try{
                            mId.setText(response.getJSONObject(0).getString("id"));
                            mTeam.setText(response.getJSONObject(0).getString("team"));
                            mName.setText(response.getJSONObject(0).getString("name"));
                            mPosition.setText(response.getJSONObject(0).getString("position"));

                            mId1.setText(response.getJSONObject(1).getString("id"));
                            mTeam1.setText(response.getJSONObject(1).getString("team"));
                            mName1.setText(response.getJSONObject(1).getString("name"));
                            mPosition1.setText(response.getJSONObject(1).getString("position"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d(TAG, error.toString());
                    }
                }
        );
        MySingleton.getInstance(this).addToRequestQueue(jsArrReuest);
    }
}
