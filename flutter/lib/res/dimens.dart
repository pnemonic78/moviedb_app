import 'package:flutter/material.dart';

const radiusCircular_8 = Radius.circular(8.0);
const radiusCircular_16 = Radius.circular(16.0);
const borderCircular_8 = BorderRadius.all(radiusCircular_8);
const borderCircularTop_8 = BorderRadius.vertical(top: radiusCircular_8);
const borderCircularLeft_8 = BorderRadius.horizontal(left: radiusCircular_8);
const borderCircular_16 = BorderRadius.all(radiusCircular_16);
const borderCircularTop_16 = BorderRadius.vertical(top: radiusCircular_16);
const borderCircularLeft_16 = BorderRadius.horizontal(left: radiusCircular_16);

const padding_4 = 4.0;

const padding_8 = 8.0;
const paddingAll_8 = EdgeInsets.all(padding_8);
const paddingHorizontal_8 = EdgeInsets.symmetric(horizontal: padding_8);
const paddingLeft_8 = EdgeInsets.only(left: padding_8);
const paddingTop_8 = EdgeInsets.only(top: padding_8);
const paddingBottom_8 = EdgeInsets.only(bottom: padding_8);
const paddingVertical_8 = EdgeInsets.symmetric(vertical: padding_8);
const paddingBelow_8 =
    EdgeInsets.only(left: padding_8, right: padding_8, bottom: padding_8);

const padding_16 = 16.0;
const paddingAll_16 = EdgeInsets.all(padding_16);
const paddingHorizontal_16 = EdgeInsets.symmetric(horizontal: padding_16);
const paddingLeft_16 = EdgeInsets.only(left: padding_16);
const paddingTop_16 = EdgeInsets.only(top: padding_16);
const paddingVertical_16 = EdgeInsets.symmetric(vertical: padding_16);

const ratioPoster = 1.5;

const posterListHeight = 200.0;
const posterListWidth = posterListHeight / ratioPoster;

const posterGridHeight = 200.0;
const posterGridWidth = posterGridHeight / ratioPoster;

const posterDetailsHeight = 300.0;
const posterDetailsWidth = posterDetailsHeight / ratioPoster;

const thumbnailHeight = 150.0;
const thumbnailWidth = thumbnailHeight * 16 / 9;

const backdropHeight = 300.0;
const backdropWidth = backdropHeight * 16 / 9;

const castTileHeight = 175.0;
const castTileWidth = castTileHeight / ratioPoster;

const personDetailsHeight = 300.0;
const personDetailsWidth = personDetailsHeight / ratioPoster;

const personIconSize = personDetailsWidth / 6;

const errorIconSize = 100.0;
