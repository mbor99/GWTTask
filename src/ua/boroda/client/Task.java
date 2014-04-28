package ua.boroda.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.LinkedList;
import java.util.List;


public class Task implements EntryPoint {
List<User> USERS;

    public void createUsers() {
        USERS = new LinkedList();
        USERS.add(new User("1Vasya","Vasya","Pupkin","vp@mail.com",User.role.admin));
        USERS.add(new User("2","Ivan","Petrov","ip@mail.com",User.role.user));
    }

    @Override
    public void onModuleLoad() {
        createUsers();

        SplitLayoutPanel p = new SplitLayoutPanel();
        p.setPixelSize(800, 600);
        p.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        p.getElement().getStyle().setBorderWidth(5, Style.Unit.PX);
        p.getElement().getStyle().setBorderColor("grey");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-VDragger"
                + "{ height: 5px !important; background: gray; }");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-HDragger"
                + "{ width: 5px !important; background: gray; }");
        p.addNorth(new HTML("datagrid"), 250);
        p.addWest(new HTML("info"), 400);
        p.add(grid(USERS));


        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(p);

    }


    public DataGrid grid(List<User> user) {
        // Create a DataGrid.

        DataGrid dataGrid = new DataGrid<User>();
        dataGrid.setWidth("100%");

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("empty datagrid"));


        // Add a selection model so we can select cells.
        final SelectionModel<User> selectionModel = new SingleSelectionModel<User>();
        dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager
                .<User>createCheckboxManager());

        // Initialize the columns.
        initTableColumns(USERS, dataGrid);

//        dataGrid.
//        dataGrid.setRowData();

        // Add the CellList to the adapter in the database.
//        ContactDatabase.get().addDataDisplay(dataGrid);

        // Create the UiBinder.
//        Binder uiBinder = GWT.create(.class);
//        return uiBinder.createAndBindUi(this);

        return dataGrid;
    }

    private void initTableColumns(List<User> users, DataGrid dataGrid) {

        // Id.
        Column<User, String> idColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User object) {
                return object.getId();
            }
        };

        dataGrid.addColumn(idColumn, "Id");
        dataGrid.setColumnWidth(idColumn, 60, Style.Unit.PCT);


        // Name.
        Column<User, String> nameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User object) {
                return object.getId();
            }
        };

        dataGrid.addColumn(nameColumn, "Name");
        dataGrid.setColumnWidth(nameColumn, 60, Style.Unit.PCT);


        // Role.
        Column<User, String> roleColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User object) {
                return object.get();
            }
        };

        dataGrid.addColumn(roleColumn, "Role");
        dataGrid.setColumnWidth(roleColumn, 60, Style.Unit.PCT);
    }


}
