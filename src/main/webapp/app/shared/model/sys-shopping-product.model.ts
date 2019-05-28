import { Moment } from 'moment';
import { ISysShoppingCar } from 'app/shared/model//sys-shopping-car.model';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysShoppingProduct {
    id?: number;
    productNumber?: number;
    productType?: number;
    createTime?: Moment;
    updateTime?: Moment;
    shoppingCar?: ISysShoppingCar;
    product?: ISysProduct;
}

export class SysShoppingProduct implements ISysShoppingProduct {
    constructor(
        public id?: number,
        public productNumber?: number,
        public productType?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public shoppingCar?: ISysShoppingCar,
        public product?: ISysProduct
    ) {}
}
