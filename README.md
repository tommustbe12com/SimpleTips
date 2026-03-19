# SimpleTips Usage Guide

A clean and customizable tip system for your Minecraft server.

---

## Features

* Unlimited tips
* Full color support (& codes)
* Placeholder support (PlaceholderAPI)
* Multiple display types (chat, actionbar, title, subtitle)
* In-game management commands
* Config and command control
* Lightweight and easy to use

---

## Commands

### General

`/tip` - Shows help menu

### View Tips

`/tip list` - Displays all saved tips with their IDs

### Add a Tip

`/tip add <message>`
Example: `/tip add &bHey %player_name%, welcome!`

### Remove a Tip

`/tip remove <id>`
Example: `/tip remove 0`

### Change Interval

`/tip interval <seconds>`
Example: `/tip interval 60`

### Change Display Type

`/tip display <type>`
Available types: chat, actionbar, title, subtitle
Example: `/tip display actionbar`

---

## Permissions

`simpletips.manage` - Required for adding tips, removing tips, changing interval, and changing display mode

---

## Placeholders

* Requires PlaceholderAPI installed
* Works in tips automatically
* Examples of simple placeholders: %player_name%, %player_displayname%, %server_online%
