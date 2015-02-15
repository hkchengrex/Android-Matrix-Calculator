package com.silver.matrixcalculator;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ZapActivity extends ActionBarActivity implements

		ActionBar.TabListener {
	
	SectionsPagerAdapter mSectionsPagerAdapter;


    static final char[] preSet = new char[]{'x' , 'y' , 'z'};
	static ViewPager mViewPager;
	static Context context;
	static TextView RDet;
	static 	Terms[][][] OM = new Terms[3][3][3];
	static EditText[][][] OE = new EditText[2][3][3];
    static TextView[][] RM = new TextView[3][3];
	static EditText focus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_zap);
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(4);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zap, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position==2){
				return ResultFragment.newInstance(position+1);
			}else{
				return PlaceholderFragment.newInstance(position + 1);
			}
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "INPUT 1";
			case 1:
				return "INPUT 2";
			case 2:
				return "OUTPUT";
			}
			return null;
		}
	}


	public static class PlaceholderFragment extends Fragment {

		private static final String ARG_SECTION_NUMBER = "section_number";
		private static int SECTION_NUMBER=0;

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_zap, container,false);
			//Keep track of current section number.. Kind of dumb?
			SECTION_NUMBER++;
			
			final Button det = (Button) rootView.findViewById(R.id.detbutton);
            final Button add = (Button) rootView.findViewById(R.id.AddButton);
            final Button sub = (Button) rootView.findViewById(R.id.SubButton);
            final Button multi = (Button) rootView.findViewById(R.id.MultiButton);
            final Button cof = (Button) rootView.findViewById(R.id.CofButton);
            final Button adj = (Button) rootView.findViewById(R.id.AdjButton);
            final Button trans = (Button) rootView.findViewById(R.id.TraButton);
            final Button inv = (Button) rootView.findViewById(R.id.InvButton);
            final Button AddX = (Button) rootView.findViewById(R.id.AddX);
            final Button AddY = (Button) rootView.findViewById(R.id.AddY);
            final Button AddZ = (Button) rootView.findViewById(R.id.AddZ);
            final Button AddN = (Button) rootView.findViewById(R.id.AddN);
            final Button AddP = (Button) rootView.findViewById(R.id.AddP);
            final Button help = (Button) rootView.findViewById(R.id.help);

            help.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("This is title").setMessage(R.string.help);
                    builder.show();
                }
            });
		
			AddX.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					focus.setText(focus.getText().toString()+'x');
					focus.setSelection(focus.length());
				}
			});
			
			AddY.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					focus.setText(focus.getText().toString()+'y');
					focus.setSelection(focus.length());
				}
			});
			
			AddZ.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					focus.setText(focus.getText().toString()+'z');
					focus.setSelection(focus.length());
				}
			});

			AddN.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					focus.setText(focus.getText().toString()+'&');
					focus.setSelection(focus.length());
				}
			});
			
			AddP.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					focus.setText(focus.getText().toString()+'^');
					focus.setSelection(focus.length());
				}
			});

               final  int n =SECTION_NUMBER;
				//Assign matrix input
				OE[n-1][0][0] = (EditText) rootView.findViewById(R.id.editText1);
                OE[n-1][0][1] = (EditText) rootView.findViewById(R.id.editText2);
                OE[n-1][0][2] = (EditText) rootView.findViewById(R.id.editText3);
                OE[n-1][1][0] = (EditText) rootView.findViewById(R.id.editText4);
                OE[n-1][1][1] = (EditText) rootView.findViewById(R.id.editText5);
                OE[n-1][1][2] = (EditText) rootView.findViewById(R.id.editText6);
                OE[n-1][2][0] = (EditText) rootView.findViewById(R.id.editText7);
                OE[n-1][2][1] = (EditText) rootView.findViewById(R.id.editText8);
                OE[n-1][2][2] = (EditText) rootView.findViewById(R.id.editText9);

				//Button for calculating determinant
				det.setOnClickListener(new OnClickListener(){
					public void onClick(View v){
                        String[][] string1 = new String[3][3];
                        String[][] string2 = new String[3][3];
                        for (int i=0;i<3;i++){
                            for (int j=0;j<3;j++) {
                                string1[i][j] = OE[0][i][j].getText().toString();
                                string2[i][j] = OE[1][i][j].getText().toString();
                                //Parse the matrix
                                OM[0][i][j] = Read(string1[i][j]);
                                OM[1][i][j] = Read(string2[i][j]);
                            }
                        }
						RDet.setText(
                            OM[n - 1][0][0].EMulti(OM[n - 1][1][1]).EMulti(OM[n - 1][2][2])
                                        .Sum(OM[n - 1][0][1].EMulti(OM[n - 1][1][2]).EMulti(OM[n - 1][2][0]))
                                        .Sum(OM[n - 1][0][2].EMulti(OM[n - 1][1][0].EMulti(OM[n - 1][2][1])))
                                        .Sub(OM[n - 1][0][2].EMulti(OM[n - 1][1][1].EMulti(OM[n - 1][2][0])))
                                        .Sub(OM[n - 1][0][1].EMulti(OM[n - 1][1][0].EMulti(OM[n - 1][2][2])))
                                        .Sub(OM[n - 1][0][0].EMulti(OM[n - 1][1][2].EMulti(OM[n - 1][2][1]))).toString());
					}
				});
            //Button for addition calculation
            add.setOnClickListener(new OnClickListener(){
                public void onClick(View v){
                    String[][] string1 = new String[3][3];
                    String[][] string2 = new String[3][3];
                    for (int i=0;i<3;i++){
                        for (int j=0;j<3;j++) {
                            string1[i][j] = OE[0][i][j].getText().toString();
                            string2[i][j] = OE[1][i][j].getText().toString();
                            //Parse the matrix
                            OM[0][i][j] = Read(string1[i][j]);
                            OM[1][i][j] = Read(string2[i][j]);
                            //Add and output the matrix
                            OM[2][i][j] = OM[0][i][j].Sum(OM[1][i][j]);
                            RM[i][j].setText(OM[2][i][j].toString());
                        }
                    }
                }
            });
                //Button for subtraction calculation
				sub.setOnClickListener(new OnClickListener(){
					public void onClick(View v){
                        String[][] string1 = new String[3][3];
                        String[][] string2 = new String[3][3];
                        for (int i=0;i<3;i++){
                            for (int j=0;j<3;j++) {
                                string1[i][j] = OE[0][i][j].getText().toString();
                                string2[i][j] = OE[1][i][j].getText().toString();
                                //Parse the matrix
                                OM[0][i][j] = Read(string1[i][j]);
                                OM[1][i][j] = Read(string2[i][j]);
                                //Add and output the matrix
                                if (n==1) {
                                    OM[2][i][j] = OM[0][i][j].Sub(OM[1][i][j]);
                                }else{
                                    OM[2][i][j] = OM[1][i][j].Sub(OM[0][i][j]);
                                }
                                RM[i][j].setText(OM[2][i][j].toString());
                            }
                        }
					}
				});

            //Button for multiplication
				multi.setOnClickListener(new OnClickListener(){
					public void onClick(View v){
                        String[][] string1 = new String[3][3];
                        String[][] string2 = new String[3][3];
                        for (int i=0;i<3;i++){
                            for (int j=0;j<3;j++) {
                                string1[i][j] = OE[0][i][j].getText().toString();
                                string2[i][j] = OE[1][i][j].getText().toString();
                                //Parse the matrix
                                OM[0][i][j] = Read(string1[i][j]);
                                OM[1][i][j] = Read(string2[i][j]);
                            }
                        }
						//Calculate and output the matrix
                        if (n==1) {
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    OM[2][i][j] = OM[0][i][0].EMulti(OM[1][0][j]).Sum(OM[0][i][1].EMulti(OM[1][1][j]).Sum(OM[0][i][2].EMulti(OM[1][2][j])));
                                    RM[i][j].setText(OM[2][i][j].toString());
                                }
                            }
                        }else{
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    OM[2][i][j] = OM[1][i][0].EMulti(OM[0][0][j]).Sum(OM[1][i][1].EMulti(OM[0][1][j]).Sum(OM[1][i][2].EMulti(OM[0][2][j])));
                                    RM[i][j].setText (OM[2][i][j] .toString());
                                }
                            }
                        }

					}
				});

            //Button for calculating cofactor
            cof.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    String[][] string = new String[3][3];
                    for (int i=0;i<3;i++){
                        for (int j=0;j<3;j++) {
                            string[i][j] = OE[n-1][i][j].getText().toString();
                            //Parse the matrix
                            OM[n-1][i][j] = Read(string[i][j]);
                        }
                    }

                    for (int i=0;i<3;i++){
                        for (int k=0;k<3;k++){
                            Terms myTerms;
                            Terms T1 = new Terms(new Term(1,0,0,0));
                            Terms T2 = new Terms(new Term(1,0,0,0));
                            //find the unsigned minor
                            int flag=2;
                            for (int o=0;o<3;o++){
                                for (int p=0;p<3;p++){
                                    if (!(i==o || k==p)){
                                        if (flag<2) {
                                            T1 =  T1.EMulti(OM[n - 1][o][p]);
                                            flag++;
                                        }else{
                                            T2 = T2.EMulti(OM[n - 1][o][p]);
                                            flag=0;
                                        }
                                    }
                                }
                            }
                            //Decide the sign of cofactor
                            if ((i+k)%2!=0) {
                                myTerms = T1.Sub(T2);
                            }else{
                                myTerms = T2.Sub(T1);
                            }
                            OM[2][i][k] = myTerms;
                            RM[i][k].setText(myTerms.toString());
                        }
                    }
                }
            });

            //Button for calculating transpose
            trans.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    String[][] string = new String[3][3];
                    for (int i=0;i<3;i++){
                        for (int j=0;j<3;j++) {
                            string[i][j] = OE[n-1][i][j].getText().toString();
                            //Parse the matrix
                            OM[n-1][i][j] = Read(string[i][j]);
                        }
                    }

                    for (int i=0;i<3;i++){
                        for (int k=0;k<3;k++){
                            OM[2][i][k] = OM[n-1][k][i];
                            RM[i][k].setText(OM[2][i][k].toString());
                        }
                    }
                }
            });

            //Button for calculating adjoint
            adj.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    cof.performClick();
                    for (int i=0;i<3;i++){
                        for (int k=0;k<3;k++){
                            RM[i][k].setText(OM[2][k][i].toString());
                        }
                    }
                }
            });

            //Button for calculating inverse
            inv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    adj.performClick();
                    det.performClick();
                    RDet.setText("1/ "+RDet.getText());
                }
            });

				//Record the last focus
				for (int i=0;i<3;i++){
                    for (int j=0;j<3;j++) {
                        OE[n-1][i][j].setOnFocusChangeListener(new OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (hasFocus) {
                                    focus = (EditText) v;
                                }
                            }

                        });
                    }
				}

			return rootView;
		}
	}

	public static class ResultFragment extends Fragment {
		
		private static final String ARG_SECTION_NUMBER = "section_number";

		public static ResultFragment newInstance(int sectionNumber) {
			ResultFragment fragment = new ResultFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public ResultFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_zip, container,false);
			RM[0][0] = (TextView) rootView.findViewById(R.id.TextView1);
            RM[0][1] = (TextView) rootView.findViewById(R.id.TextView2);
            RM[0][2] = (TextView) rootView.findViewById(R.id.TextView3);
            RM[1][0] = (TextView) rootView.findViewById(R.id.TextView4);
            RM[1][1] = (TextView) rootView.findViewById(R.id.TextView5);
            RM[1][2] = (TextView) rootView.findViewById(R.id.TextView6);
            RM[2][0] = (TextView) rootView.findViewById(R.id.TextView7);
            RM[2][1] = (TextView) rootView.findViewById(R.id.TextView8);
            RM[2][2] = (TextView) rootView.findViewById(R.id.TextView9);
			RDet = (TextView) rootView.findViewById(R.id.num);
            final Button help = (Button) rootView.findViewById(R.id.button2);

            help.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("This is title").setMessage(R.string.help);
                    builder.show();
                }
            });

			return rootView;
		}
	}
	
	//Read from string to terms
	public static Terms Read(String instring){
        String org = instring;
        try {
            if (instring.length() == 0) {
                return new Terms(new Term(0, 0, 0, 0));
            }
            Terms myTerms = new Terms();

            boolean InTerms = true;
            boolean InTerm = true;

            while (InTerms) {
                int n;
                int i = 0;

                //Separate addition and subtraction (just like addition actually) of different terms
                n = instring.indexOf("&");
                if (n == -1) {
                    n = instring.length() - 1;
                    InTerms = false;
                }

                String tmp = instring.substring(0, n + 1);
                instring = instring.substring(n + 1);
                Term myTerm = new Term();
                boolean done = false;
                int pos = tmp.length() - 1;
                for (int k = 0; k < 3; k++) {
                    int temp = tmp.indexOf(preSet[k]);
                    if (temp <= pos && temp != -1) {
                        pos = temp;
                        done = true;
                    }
                }
                if (done) {
                    myTerm.number[0] = Integer.valueOf(tmp.substring(0, pos));
                } else {
                    myTerm.number[0] = Integer.valueOf(tmp);
                }

                while (InTerm) {
                    int index;
                    index = tmp.indexOf(preSet[i]);

                    if (index != -1) {
                        boolean hasPower = false;
                        boolean IsNeg = false;

                        try {
                            //Sometimes it exceeds the range
                            hasPower = tmp.charAt(index + 1) == '^';
                            IsNeg = tmp.charAt(index + 2) == '-';
                        } catch (Exception e) {
                            //Seriously, Nothing is needed to be done here, I guess
                            //hmm... I hope.
                        }

                        if (hasPower) {
                            if (IsNeg) {
                                //Negative power
                                myTerm.number[i + 1] = -1 * Character.getNumericValue(tmp.charAt(index + 3));
                            } else {
                                //Positive power
                                myTerm.number[i + 1] = Character.getNumericValue(tmp.charAt(index + 2));
                            }
                        } else {
                            //When the power is implicitly set as 1
                            myTerm.number[i + 1] = 1;
                        }

                    } else {
                        //When there are no such term, i.e. power = 0
                        myTerm.number[i + 1] = 0;
                    }

                    if (i == 2) {
                        InTerm = false;
                    }
                    i++;
                }
                InTerm = true;
                myTerms.add(myTerm);
            }
            return myTerms;
        }catch(Exception e){
            Toast.makeText(context,"Opps~ Unresolvable error encountered in Input: "+org+"\nI'm Sorry ;("+"\nPlease consider reading the instruction or giving us comment!", Toast.LENGTH_LONG).show();
            return new Terms();
        }
		
	}	
	

}
