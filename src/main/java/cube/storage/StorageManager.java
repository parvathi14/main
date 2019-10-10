package cube.storage;

import cube.model.food.Food;
import cube.model.food.FoodList;

import java.io.Serializable;
import java.util.ArrayList;

// this is facade design pattern
// reference: https://github.com/nusCS2113-AY1920S1/addressbook-level3/tree/master/src/main/java/seedu/address/storage

public class StorageManager implements Serializable {
	private FoodStorage foodStorage;
	private RevenueStorage revenueStorage;

	public StorageManager() {
		this.foodStorage = new FoodStorage();
		this.revenueStorage = new RevenueStorage();
	}

	public StorageManager(FoodStorage foodStorage, RevenueStorage revenueStorage) {
		this.foodStorage = foodStorage;
		this.revenueStorage = revenueStorage;
	}

	public FoodList loadFood() {
		return foodStorage.loadFood();
	}

	public void appendFood(Food food) {
		foodStorage.appendFood(food);
	}

	public void storeFoodList(FoodList foodlist) {
		foodStorage.storeFoodList(foodlist);
	}

	public double loadRevenue() {
		return revenueStorage.loadRevenue();
	}

	public void storeRevenue(double revenue) {
		revenueStorage.storeRevenue(revenue);
	}

}