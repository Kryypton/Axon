package com.radonn.axon.models.wow.models.equipment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.radonn.axon.models.wow.models.base.*;

public class EquippedItem {

    private Item item;
	private List<Enchantement> enchantments;
	private List<Sockets> sockets;
    private Descriptor slot;
    private int quantity;
    private int context;
    private Descriptor quality;
    private String name;
    @JsonProperty("modified_appearance_id")
    private int modifiedAppearanceId;
    private Item media;
    @JsonProperty("item_class")
    private Playable itemClass;
    @JsonProperty("item_subclass")
    private Playable itemSubclass;
    @JsonProperty("inventory_type")
    private Descriptor inventoryType;
    private Descriptor binding;
    private Armor armor;
    private List<Stat> stats;
    @JsonProperty("sell_price")
    private SellPrice sellPrice;
    private Requirements requirements;
    private ValuedString level;
    @JsonProperty("transmog")
    private Transmog transmog;
    private ValuedString durability;
    @JsonProperty("name_description")
    private Display nameDescription;

	public Item getItem() {
		return item;
	}

	public List<Enchantement> getEnchantments() {
		return enchantments;
	}

	public List<Sockets> getSockets() {
		return sockets;
	}

	public Descriptor getSlot() {
		return slot;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getContext() {
		return context;
	}

	public Descriptor getQuality() {
		return quality;
	}

	public String getName() {
		return name;
	}

	public int getModifiedAppearanceId() {
		return modifiedAppearanceId;
	}

	public Item getMedia() {
		return media;
	}

	public Playable getItemClass() {
		return itemClass;
	}

	public Playable getItemSubclass() {
		return itemSubclass;
	}

	public Descriptor getInventoryType() {
		return inventoryType;
	}

	public Descriptor getBinding() {
		return binding;
	}

	public Armor getArmor() {
		return armor;
	}

	public List<Stat> getStats() {
		return stats;
	}

	public SellPrice getSellPrice() {
		return sellPrice;
	}

	public Requirements getRequirements() {
		return requirements;
	}

	public ValuedString getLevel() {
		return level;
	}

	public Transmog getTransmog() {
		return transmog;
	}

	public ValuedString getDurability() {
		return durability;
	}

	public Display getNameDescription() {
		return nameDescription;
	}
}
