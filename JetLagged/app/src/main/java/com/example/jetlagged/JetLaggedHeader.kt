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

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.jetlagged.ui.theme.*

@Preview(showBackground = true)
@Composable
fun JetLaggedHeader(modifier: Modifier = Modifier,
                    onDrawerClicked: () -> Unit = {}) {
	Box(modifier = modifier.height(150.dp)) {
		Row(modifier = Modifier.windowInsetsPadding(insets = WindowInsets.systemBars)) {
			IconButton(onClick = onDrawerClicked) {
				Icon(imageVector = Icons.Default.Menu,
				     contentDescription = stringResource(R.string.not_implemented))
			}
			
			Text(text = stringResource(id = R.string.jetlagged_app_heading),
			     modifier = Modifier
					     .fillMaxWidth()
					     .padding(top = 8.dp),
			     style = TitleBarStyle,
			     textAlign = TextAlign.Start)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun JetLaggedSleepSummary(modifier: Modifier = Modifier) {
	Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
		Column {
			Text(text = stringResource(id = R.string.average_time_in_bed_heading),
			     style = SmallHeadingStyle)
			Text(text = stringResource(id = R.string.placeholder_text_ave_time),
			     style = HeadingStyle)
		}
		Spacer(modifier = Modifier.width(16.dp))
		Column {
			Text(text = stringResource(id = R.string.average_sleep_time_heading),
			     style = SmallHeadingStyle)
			Text(text = stringResource(R.string.placeholder_text_ave_time_2),
			     style = HeadingStyle)
		}
	}
	Spacer(modifier = Modifier.height(32.dp))
}
