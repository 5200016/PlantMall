import { Moment } from 'moment';
import { ISysClassify } from 'app/shared/model//sys-classify.model';
import { ISysOrder } from 'app/shared/model//sys-order.model';
import { ISysShoppingCar } from 'app/shared/model//sys-shopping-car.model';
import { ISysReview } from 'app/shared/model//sys-review.model';
import { ISysCollection } from 'app/shared/model//sys-collection.model';

export interface ISysProduct {
    id?: number;
    name?: string;
    price?: number;
    image?: string;
    specification?: string;
    inventory?: number;
    sale?: number;
    description?: any;
    createTime?: Moment;
    updateTime?: Moment;
    classify?: ISysClassify;
    orders?: ISysOrder[];
    shoppingCars?: ISysShoppingCar[];
    reviews?: ISysReview[];
    collections?: ISysCollection[];
}

export class SysProduct implements ISysProduct {
    constructor(
        public id?: number,
        public name?: string,
        public price?: number,
        public image?: string,
        public specification?: string,
        public inventory?: number,
        public sale?: number,
        public description?: any,
        public createTime?: Moment,
        public updateTime?: Moment,
        public classify?: ISysClassify,
        public orders?: ISysOrder[],
        public shoppingCars?: ISysShoppingCar[],
        public reviews?: ISysReview[],
        public collections?: ISysCollection[]
    ) {}
}
