package id.suryaproject.a25nabi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import id.suryaproject.a25nabi.model.ModelMain;

public class DetailActivity extends AppCompatActivity {

    ModelMain modelMain;
    String dtname, dtltahun, dtlusia, dtlDesc, dtlTmp;
    TextView tvThn, tvUsia, tvDesc, tvTmp;

    Toolbar tbDetail;
    ImageView imageViewHeader;
    ProgressBar progressBar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSdk();
        init();
        implementation();
        loadData();
    }

    private void setSdk() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT>=21){
            setWindowFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    private void init() {
        tvThn = findViewById(R.id.thn);
        tvUsia = findViewById(R.id.usia);
        tvDesc = findViewById(R.id.description);
        tvTmp = findViewById(R.id.tmp);
        tbDetail = findViewById(R.id.tbDetail);
        imageViewHeader = findViewById(R.id.cover);
        progressBar = findViewById(R.id.progress);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

    }

    private void implementation() {
        setSupportActionBar(tbDetail);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        modelMain = (ModelMain) getIntent().getSerializableExtra("data");
        //Toast.makeText(this, ""+modelMain.getImage_url(), Toast.LENGTH_SHORT).show();
    }
    private void loadData() {
        Glide.with(this)
                .load(modelMain.getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageViewHeader);

        if (modelMain!=null){
            dtname= modelMain.getName();
            dtlusia =modelMain.getUsia();
            dtltahun = modelMain.getThn_kelahiran();
            dtlTmp = modelMain.getTmp();
            dtlDesc = modelMain.getDescription();

            collapsingToolbarLayout.setTitle(dtname);
            tvTmp .setText(dtlTmp);
            tvThn.setText(dtltahun);
            tvUsia.setText(dtlusia);
            tvDesc.setText(dtlDesc);

        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}