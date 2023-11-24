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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.jetlagged.ui.theme.*
import java.time.*
import java.time.format.*
import java.util.*

@Preview(showBackground = true)
@Preview(device = Devices.FOLDABLE, showBackground = true)
@Composable
fun JetLaggedScreen(modifier: Modifier = Modifier, onDrawerClicked: () -> Unit = {}) {
	Column(modifier = modifier
			.background(Color.White)
			.fillMaxSize()
			.verticalScroll(rememberScrollState())) {
		Column(modifier = Modifier.yellowBackground()) {
			JetLaggedHeader(onDrawerClicked = onDrawerClicked,
			                modifier = Modifier.fillMaxWidth())
			Spacer(modifier = Modifier.height(32.dp))
			JetLaggedSleepSummary(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
		}
		
		Spacer(modifier = Modifier.height(16.dp))
		
		var selectedTab by remember { mutableStateOf(SleepTab.Week) }
		JetLaggedHeaderTabs(onTabSelected = { selectedTab = it },
		                    selectedTab = selectedTab)
		
		Spacer(modifier = Modifier.height(16.dp))
		val sleepState by remember {
			mutableStateOf(sleepData)
		}
		JetLaggedTimeGraph(sleepState)
	}
}

@Composable
private fun JetLaggedTimeGraph(sleepGraphData: SleepGraphData) {
	val scrollState = rememberScrollState()
	
	val hours = (sleepGraphData.earliestStartHour..23) + (0..sleepGraphData.latestEndHour)
	
	TimeGraph(modifier = Modifier
			.horizontalScroll(scrollState)
			.wrapContentSize(),
	          dayItemsCount = sleepGraphData.sleepDayData.size,
	          hoursHeader = {
		          HoursHeader(hours)
	          },
	          dayLabel = { index ->
		          val data = sleepGraphData.sleepDayData[index]
		          DayLabel(data.startDate.dayOfWeek)
	          },
	          bar = { index ->
		          val data = sleepGraphData.sleepDayData[index]
		          // We have access to Modifier.timeGraphBar() as we are now in TimeGraphScope
		          SleepBar(sleepData = data,
		                   modifier = Modifier
				                   .padding(bottom = 8.dp)
				                   .timeGraphBar(
						                   start = data.firstSleepStart,
						                   end = data.lastSleepEnd,
						                   hours = hours,
				                                ))
	          })
}

@Composable
private fun DayLabel(dayOfWeek: DayOfWeek) {
	Text(text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
	     modifier = Modifier
			     .height(24.dp)
			     .padding(start = 8.dp,
			              end = 24.dp),
	     style = SmallHeadingStyle,
	     textAlign = TextAlign.Center)
}

@Composable
private fun HoursHeader(hours: List<Int>) {
	Row(Modifier
			    .padding(bottom = 16.dp)
			    .drawBehind {
				    val brush = Brush.linearGradient(listOf(YellowVariant, Yellow))
				    drawRoundRect(brush = brush,
				                  cornerRadius = CornerRadius(x = 10.dp.toPx(),
				                                              y = 10.dp.toPx()))
			    }) {
		hours.forEach {
			Text(text = "$it",
			     textAlign = TextAlign.Center,
			     modifier = Modifier
					     .width(50.dp)
					     .padding(vertical = 4.dp),
			     style = SmallHeadingStyle)
		}
	}
}
