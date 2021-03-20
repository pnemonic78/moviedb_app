export class Utils {
    // FIXME use "luxon" Duration.
    static formatDuration(value: number): string {
        let minutes = (value % 60).toString().padStart(2, "0")
        let hours = Math.floor(value / 60).toString().padStart(2, "0")
        return hours + ":" + minutes
    }

    static formatCurrency(value: number): string {
        return "$" + value
    }

    static formatPercent(value: number): string {
        return value + "%"
    }

    static formatDate(value: Date | null): string {
        return value?.toString() ?? ""
    }
}
