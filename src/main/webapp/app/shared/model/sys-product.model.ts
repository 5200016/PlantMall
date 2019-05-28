import { Moment } from 'moment';
import { ISysClassify } from 'app/shared/model//sys-classify.model';
import { ISysProductImage } from 'app/shared/model//sys-product-image.model';
import { ISysShoppingProduct } from 'app/shared/model//sys-shopping-product.model';
import { ISysReview } from 'app/shared/model//sys-review.model';
import { ISysCollection } from 'app/shared/model//sys-collection.model';

export interface ISysProduct {
    id?: number;
    name?: string;
    leasePrice?: number;
    price?: number;
    image?: string;
    inventory?: number;
    sale?: number;
    description?: any;
    createTime?: Moment;
    updateTime?: Moment;
    classifies?: ISysClassify[];
    images?: ISysProductImage[];
    shoppingProducts?: ISysShoppingProduct[];
    reviews?: ISysReview[];
    collections?: ISysCollection[];
}

export class SysProduct implements ISysProduct {
    constructor(
        public id?: number,
        public name?: string,
        public leasePrice?: number,
        public price?: number,
        public image?: string,
        public inventory?: number,
        public sale?: number,
        public description?: any,
        public createTime?: Moment,
        public updateTime?: Moment,
        public classifies?: ISysClassify[],
        public images?: ISysProductImage[],
        public shoppingProducts?: ISysShoppingProduct[],
        public reviews?: ISysReview[],
        public collections?: ISysCollection[]
    ) {}
}
