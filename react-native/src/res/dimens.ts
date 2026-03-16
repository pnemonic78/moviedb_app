const cardRadius = 16

const ratioPoster = 1.5

const posterListHeight = 200.0
const posterListWidth = posterListHeight / ratioPoster

const posterGridHeight = 200.0
const posterGridRatio = ratioPoster
const posterGridWidth = posterGridHeight / posterGridRatio

const posterDetailsHeight = 300.0
const posterDetailsWidth = posterDetailsHeight / ratioPoster

const thumbnailHeight = 150.0
const thumbnailWidth = thumbnailHeight * 16 / 9

const backdropHeight = 300.0
const backdropWidth = backdropHeight * 16 / 9

const castTileWidth = 150
const castTileHeight = castTileWidth * ratioPoster

const personDetailsHeight = 300.0
const personDetailsWidth = personDetailsHeight / ratioPoster

const personIconSize = personDetailsWidth / 6

const errorIconSize = 100.0

const actionBarButtonMargin = 8
const actionBarButtonSize = 34

export const dimens = {
    cardRadius: cardRadius,

    ratioPoster: ratioPoster,

    posterListHeight: posterListHeight,
    posterListWidth: posterListWidth,

    posterGridWidth: posterGridWidth,
    posterGridRatio: posterGridRatio,
    posterGridHeight: posterGridHeight,

    posterDetailsHeight: posterDetailsHeight,
    posterDetailsWidth: posterDetailsWidth,

    thumbnailHeight: thumbnailHeight,
    thumbnailWidth: thumbnailWidth,

    backdropHeight: backdropHeight,
    backdropWidth: backdropWidth,

    castTileHeight: castTileHeight,
    castTileWidth: castTileWidth,

    personDetailsHeight: personDetailsHeight,
    personDetailsWidth: personDetailsWidth,

    personIconSize: personIconSize,

    errorIconSize: errorIconSize,

    actionBarButtonMargin: actionBarButtonMargin,
    actionBarButtonSize: actionBarButtonSize,
}