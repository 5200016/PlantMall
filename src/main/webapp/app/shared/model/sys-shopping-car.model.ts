import { Moment } from 'moment';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysShoppingCar {
    id?: number;
    createTime?: Moment;
    updateTime?: Moment;
    product?: ISysProduct;
}

export class SysShoppingCar implements ISysShoppingCar {
    constructor(public id?: number, public createTime?: Moment, public updateTime?: Moment, public product?: ISysProduct) {}
}
