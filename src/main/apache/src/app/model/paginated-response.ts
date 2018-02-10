export class PaginatedResponse<T> {

  private _data: T[];

  private _totalCount: number;

  get data(): T[] {
    return this._data;
  }

  set data(value: T[]) {
    this._data = value;
  }

  get totalCount(): number {
    return this._totalCount;
  }

  set totalCount(value: number) {
    this._totalCount = value;
  }
}
