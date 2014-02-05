package com.funkypantssoftware.communique;

import android.accounts.AccountManager;
import android.content.Context;

import com.funkypantssoftware.communique.authenticator.BootstrapAuthenticatorActivity;
import com.funkypantssoftware.communique.authenticator.LogoutService;
import com.funkypantssoftware.communique.core.CheckIn;
import com.funkypantssoftware.communique.core.TimerService;
import com.funkypantssoftware.communique.ui.BootstrapTimerActivity;
import com.funkypantssoftware.communique.ui.CarouselActivity;
import com.funkypantssoftware.communique.ui.CheckInsListFragment;
import com.funkypantssoftware.communique.ui.ItemListFragment;
import com.funkypantssoftware.communique.ui.NewsActivity;
import com.funkypantssoftware.communique.ui.NewsListFragment;
import com.funkypantssoftware.communique.ui.UserActivity;
import com.funkypantssoftware.communique.ui.UserListFragment;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module
(
        complete = false,

        injects = {
                BootstrapApplication.class,
                BootstrapAuthenticatorActivity.class,
                CarouselActivity.class,
                BootstrapTimerActivity.class,
                CheckInsListFragment.class,
                NewsActivity.class,
                NewsListFragment.class,
                UserActivity.class,
                UserListFragment.class,
                TimerService.class
        }

)
public class BootstrapModule  {

    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
        return new LogoutService(context, accountManager);
    }

}
