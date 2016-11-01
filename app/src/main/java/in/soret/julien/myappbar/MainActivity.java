package in.soret.julien.myappbar;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { /* AppCompat Activity est important ici. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();


        final TextView hw = (TextView) findViewById(R.id.helloworld);
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                /* Ici on met du code qui sera executé à chaque changement
                 dans le champs de recherche. */
                hw.setTextColor(Color.BLACK);
                hw.setText(query);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                /* Ici on met du code qui sera executé quand le champs de recherche sera validé. */
                hw.setTextColor(Color.BLUE);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Option Paramètres selectionnée", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_edit:
                Toast.makeText(this, "Option Editer selectionnée", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_save:
                Toast.makeText(this, "Option Sauvegarder selectionnée", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
