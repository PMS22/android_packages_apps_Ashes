/*
 * Copyright (C) 2017 FireHound ROMs
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
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import java.util.ArrayList;
import java.util.List;

import com.fh.settings.PagerSlidingTabStrip;
import com.fh.settings.fragments.statusbar.StatusBarSettings;
import com.fh.settings.fragments.qs.QuickSettings;
import com.fh.settings.fragments.notifications.FhNotificationsSettings;
import com.fh.settings.fragments.lockscreen.LockScreenSettings;
import com.fh.settings.fragments.recents.RecentsSettings;
import com.fh.settings.fragments.hwbutton.ButtonSettings;
import com.fh.settings.fragments.navbar.NavbarSettings;
import com.fh.settings.fragments.ui.DisplaySettings;
import com.fh.settings.fragments.sound.FhSoundSettings;
import com.fh.settings.fragments.animation.AnimationSettings;
import com.fh.settings.fragments.misc.MiscSettings;
import com.fh.settings.fragments.firehound.Firehound;

public class FhAshes extends SettingsPreferenceFragment {

    private static final int MENU_HELP  = 0;

    ViewPager mViewPager;
    String titleString[];
    ViewGroup mContainer;
    PagerSlidingTabStrip mTabs;

    static Bundle mSavedState;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;

        View view = inflater.inflate(R.layout.ashes, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mTabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        StatusBarAdapter StatusBarAdapter = new StatusBarAdapter(getFragmentManager());
        mViewPager.setAdapter(StatusBarAdapter);
        mTabs.setViewPager(mViewPager);

        setHasOptionsMenu(true);
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

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.FH_SETTINGS;
    }

    @Override
    public void onResume() {
        super.onResume();
        mContainer.setPadding(30, 30, 30, 30);
    }

    class StatusBarAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public StatusBarAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new Firehound();
            frags[1] = new StatusBarSettings();
            frags[2] = new QuickSettings();
            frags[3] = new FhNotificationsSettings();
            frags[4] = new LockScreenSettings();
            frags[5] = new RecentsSettings();
            frags[6] = new ButtonSettings();
            frags[7] = new NavbarSettings();
            frags[8] = new DisplaySettings();
            frags[9] = new FhSoundSettings();
            frags[10] = new AnimationSettings();
            frags[11] = new MiscSettings();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[]{
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
}
