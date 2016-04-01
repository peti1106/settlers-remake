/*******************************************************************************
 * Copyright (c) 2016
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package jsettlers.main.swing.menu.mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jsettlers.common.menu.IStartingGame;
import jsettlers.logic.map.save.loader.SavegameLoader;
import jsettlers.logic.player.PlayerSetting;
import jsettlers.main.JSettlersGame;
import jsettlers.main.swing.JSettlersFrame;
import jsettlers.main.swing.menu.openpanel.OpenPanel;

/**
 * @author codingberlin
 */
public class StartSaveGame implements ActionListener {

	private final JSettlersFrame settlersFrame;
	private OpenPanel relatedOpenPanel;

	public StartSaveGame(JSettlersFrame settlersFrame) {
		this.settlersFrame = settlersFrame;
	}

	public void setRelatedOpenPanel(OpenPanel relatedOpenPanel) {
		this.relatedOpenPanel = relatedOpenPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SavegameLoader savegameLoader = (SavegameLoader) relatedOpenPanel.getSelectedMap();
		if (savegameLoader != null) {
			// TODO: read playersettings out of savegame file
			long randomSeed = 4711L;
			byte playerId = 0;
			PlayerSetting[] playerSettings = PlayerSetting.createDefaultSettings(playerId, (byte) savegameLoader.getMaxPlayers());
			JSettlersGame game = new JSettlersGame(savegameLoader, randomSeed, playerId, playerSettings);
			IStartingGame startingGame = game.start();
			settlersFrame.showStartingGamePanel(startingGame);
		}
	}
}