export class Utils {
    // FIXME use formatter, e.g. "luxon" Duration.
    static formatDuration(value: number): string {
        let minutes = (value % 60).toString().padStart(2, "0")
        let hours = Math.floor(value / 60).toString().padStart(2, "0")
        return hours + ":" + minutes
    }

    // FIXME use formatter
    static formatCurrency(value: number): string {
        return "$" + value
    }

    // FIXME use formatter
    static formatPercent(value: number): string {
        return value + "%"
    }

    static formatDate(value: Date | null | undefined): string {
        if ((value === null) || (value === undefined)) return ""
        if (Utils.isString(value)) {
            value = new Date(value)
        }
        return value.toLocaleDateString()
    }

    static isString(a: any): boolean {
        if (typeof a === 'string' || a instanceof String) {
            return true
        }
        return false
    }
}
