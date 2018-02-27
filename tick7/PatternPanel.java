package uk.ac.cam.ap801.tick7;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class PatternPanel extends JPanel {
	
	private JList guiList;
	private Pattern currentPattern;
	private List<Pattern> patternList;
	
	public abstract void onPatternChange();
	
	public Pattern getCurrentPattern(){
		return currentPattern;
	}
	 
	public PatternPanel() {
		super();
		currentPattern = null;
		setLayout(new BorderLayout());
		guiList = new JList();
		add(new JScrollPane(guiList));
		guiList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && (patternList != null)) {
					int sel = guiList.getSelectedIndex();
					if (sel != -1) {
						currentPattern = patternList.get(sel);
						onPatternChange();
					}
				}
			 }
		});
	 }
	 
	public void setPatterns(List<Pattern> list) {
		patternList = list;
		if (list == null) {
			 currentPattern = null; //if list is null, then no valid pattern
			 guiList.setListData(new String[]{}); //no list item to select
			 return;
		}
		ArrayList<String> names = new ArrayList<String>();
		for (Pattern p : list){
			String patAuthor = p.getAuthor();
			String patName = p.getName();
			String patDescription = patName + " (" + patAuthor + ")";
			names.add(patDescription);
		}
		guiList.setListData(names.toArray());
		currentPattern = list.get(0); //select first element in list
		guiList.setSelectedIndex(0); //select first element in guiList
	 } 

}
