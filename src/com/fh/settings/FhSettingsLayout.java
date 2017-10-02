/*
 * Copyright (C) 2016 Firehound ROMs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fh.settings;

import android.app.Activity;
import android.content.Context;
import android.content.ContentResolver;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.internal.logging.nano.MetricsProto;

import com.fh.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import com.fh.settings.fragments.statusbar.StatusBarSettings;
import com.fh.settings.fragments.qs.QuickSettings;
import com.fh.settings.fragments.notifications.NotificationsSettings;
import com.fh.settings.fragments.lockscreen.LockScreenSettings;
import com.fh.settings.fragments.recents.RecentsSettings;
import com.fh.settings.fragments.hwbutton.ButtonSettings;
import com.fh.settings.fragments.navbar.NavbarSettings;
import com.fh.settings.fragments.ui.DisplaySettings;
import com.fh.settings.fragments.sound.SoundSettings;
import com.fh.settings.fragments.animation.AnimationSettings;
import com.fh.settings.fragments.misc.MiscSettings;
import com.fh.settings.fragments.firehound.Firehound;


public class FhSettingsLayout extends SettingsPreferenceFragment {

    private static final String TAG = "FhSettingsLayout";
    ViewPager mViewPager;
    ViewGroup mContainer;
    PagerSlidingTabStrip mTabs;
    SectionsPagerAdapter mSectionsPagerAdapter;
    protected Context mContext;
    private LinearLayout mLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
        View view = inflater.inflate(R.layout.fh_settings, container, false);
        mLayout = (LinearLayout) view.findViewById(R.id.fh_content);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabs.setViewPager(mViewPager);
        mContext = getActivity().getApplicationContext();
        ContentResolver resolver = getActivity().getContentResolver();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new Firehound();
            frags[1] = new StatusSarSettings();
            frags[2] = new QuickSettings();
            frags[3] = new NotificationsSettings();
            frags[4] = new LockScreenSettings();
            frags[5] = new RecentsSettings();
            frags[6] = new Buttonsettings();
            frags[7] = new NavbarSettings();
            frags[8] = new DisplaySettings();
            frags[9] = new SoundSettings();
            frags[10] = new AnimationSettings();
            frags[11] = new MiscSettings();

        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[] {
            getString(R.string.fh_online),
            getString(R.string.status_bar_title),
            getString(R.string.qs_title),
            getString(R.string.notification_title),
            getString(R.string.lockscreen_title),
            getString(R.string.recents_settings),
            getString(R.string.button_title),
            getString(R.string.navbar_title),
            getString(R.string.display_title),
            getString(R.string.sound_title),
            getString(R.string.animation_settings),
            getString(R.string.misc_title)
        };
        return titleString;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.FH_SETTINGS;
     }
}
