package com.cxexpert.jetpack2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.cxexpert.jetpack2.ui.home.MainActivity
import com.cxexpert.jetpack2.utils.DummyData
import com.cxexpert.jetpack2.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class MainActivityTest {

    private val dummyMovie = DummyData.getDummyMovie()
    private val dummySeries = DummyData.getDummyTVSeries()
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)
    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
        IdlingPolicies.setMasterPolicyTimeout(3, TimeUnit.MINUTES);
        IdlingPolicies.setIdlingResourceTimeout(3, TimeUnit.MINUTES);
    }
    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun getMovie(){
        onView(withId(R.id.rv_movie))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun getMovieDetails(){
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.content_detail_name)).perform(click())
        onView(withId(R.id.detail_image)).check(matches(isDisplayed()))
        onView(withId(R.id.content_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.content_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun getTVSeries(){
        onView(withId(R.id.rv_series))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_series))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummySeries.size))
    }

    @Test
    fun getTVSeriesDetails(){
        onView(withId(R.id.rv_series)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        click()))
        onView(withId(R.id.content_detail_name_tv_series)).perform(click())
        onView(withId(R.id.detail_image_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.content_rating_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.content_overview_tv_series)).check(matches(isDisplayed()))
    }
}