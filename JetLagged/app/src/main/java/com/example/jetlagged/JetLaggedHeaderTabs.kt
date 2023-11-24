/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetlagged

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import com.example.jetlagged.ui.theme.*

enum class SleepTab(val title: Int) {
	Day(R.string.sleep_tab_day_heading),
	Week(R.string.sleep_tab_week_heading),
	Month(R.string.sleep_tab_month_heading),
	SixMonths(R.string.sleep_tab_six_months_heading),
	OneYear(R.string.sleep_tab_one_year_heading)
}

@Composable
fun JetLaggedHeaderTabs(onTabSelected: (SleepTab) -> Unit,
                        selectedTab: SleepTab,
                        modifier: Modifier = Modifier) {
	ScrollableTabRow(modifier = modifier,
	                 edgePadding = 12.dp,
	                 selectedTabIndex = selectedTab.ordinal,
	                 containerColor = White,
	                 indicator = { tabPositions: List<TabPosition> ->
		                 Box(Modifier
				                     .tabIndicatorOffset(tabPositions[selectedTab.ordinal])
				                     .fillMaxSize()
				                     .padding(horizontal = 2.dp)
				                     .border(BorderStroke(2.dp, Yellow), RoundedCornerShape(10.dp)))
	                 },
	                 divider = { }) {
		SleepTab.values().forEachIndexed { index, sleepTab ->
			val selected = index == selectedTab.ordinal
			SleepTabText(sleepTab = sleepTab,
			             selected = selected,
			             onTabSelected = onTabSelected,
			             index = index)
		}
	}
}

private val textModifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp)

@Composable
private fun SleepTabText(
		sleepTab: SleepTab,
		selected: Boolean,
		index: Int,
		onTabSelected: (SleepTab) -> Unit,
                        ) {
	Tab(modifier = Modifier
			.padding(horizontal = 2.dp)
			.clip(RoundedCornerShape(16.dp)),
	    selected = selected,
	    unselectedContentColor = Color.Black,
	    selectedContentColor = Color.Black,
	    onClick = {
		    onTabSelected(SleepTab.values()[index])
	    }) {
		Text(modifier = textModifier,
		     text = stringResource(id = sleepTab.title),
		     style = SmallHeadingStyle)
	}
}
