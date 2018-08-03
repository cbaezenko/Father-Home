package com.example.baeza.fatherhome.ui.view.bible;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baeza.fatherhome.BuildConfig;
import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.network.ApiUtils;
import com.example.baeza.fatherhome.network.JsonRequestManager;
import com.example.baeza.fatherhome.network.JsonRequestPresenter;
import com.example.baeza.fatherhome.ui.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.ArrayAdapter.createFromResource;

public class BibleFragment extends Fragment implements JsonRequestManager.View {

    private static final int MATHEW = 0;
    private static final int MARK = 1;
    private static final int LUKE = 2;
    private static final int JHON = 3;

    @BindView(R.id.spinner_book)
    Spinner mSpinnerBook;

    @BindView(R.id.spinner_chapter)
    Spinner mSpinnerChapter;

    @BindView(R.id.tv_bible)
    TextView mTextViewBible;

    @BindView(R.id.button)
    ImageButton mButton;

    @BindView(R.id.tv_no_connectivity) TextView mTextView_noConnectivity;

    private String book;
    private int chapter;

    private ArrayAdapter<CharSequence> mAdapter;

    private JsonRequestPresenter mJsonRequestPresenter;

    public BibleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        initSpinner();

        if(isNetworkConnection()) {
            mTextView_noConnectivity.setVisibility(View.INVISIBLE);
            showBible(ApiUtils.getBookOfMathew(), 1);
        }else{
            mTextView_noConnectivity.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    private boolean isNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bible, container, false);
        ButterKnife.bind(this, rootView);

        mJsonRequestPresenter = new JsonRequestPresenter(this);


        return rootView;
    }

    @Override
    public void refreshTextView(String result) {
        mTextViewBible.setText(result);
    }

    private void initSpinner() {
        mAdapter = createFromResource(getContext(),
                R.array.books_array, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBook.setAdapter(mAdapter);

        mSpinnerBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case MATHEW: {
                        setSpinnerChapter(R.array.chapters_mathew_array);
                        book = ApiUtils.getBookOfMathew();
                        break;
                    }
                    case MARK: {
                        setSpinnerChapter(R.array.chapters_mark_array);
                        book = ApiUtils.getBookOfMark();
                        break;
                    }
                    case LUKE: {
                        setSpinnerChapter(R.array.chapters_luke_array);
                        book = ApiUtils.getBookOfLuke();
                        break;
                    }
                    case JHON: {
                        setSpinnerChapter(R.array.chapters_jhon_array);
                        book = ApiUtils.getBookOfJhon();
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerChapter(int arrayLocation) {
        final ArrayAdapter<CharSequence> chapterAdapter = createFromResource(getContext(),
                arrayLocation,
                android.R.layout.simple_spinner_item);

        chapterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChapter.setAdapter(chapterAdapter);
        mSpinnerChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            chapter = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.button) public void onClick(){
        showBible(book, chapter);
    }

    private void showBible(String book, int chapter){
        String bibleVersion = "";
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.SHARED_PREF_LANG, Context.MODE_PRIVATE);
        switch (sharedPreferences.getString(Constants.SHARED_PREF_LANG, Constants.RUSSIAN_LANGUAGE)){
            case Constants.RUSSIAN_LANGUAGE:{
                bibleVersion = ApiUtils.getRussianSynodalBibleTranslation();
                break;
            }
            case Constants.ENGLISH_LANGUAGE:{
                bibleVersion = ApiUtils.getTheLexhamEnglishBible();
                break;
            }
            case Constants.SPANISH_LANGUAGE:{
                bibleVersion = ApiUtils.getReinaValeraRevisada();
                break;
            }
            case Constants.PORTUGUESE_LANGUAGE:{
                bibleVersion = ApiUtils.getVersaoFacilDeLer();
                break;
            }
        }
        mJsonRequestPresenter.requestJsonModel(bibleVersion,
                book,
                chapter,
                BuildConfig.bibleApiKey);
    }
}
