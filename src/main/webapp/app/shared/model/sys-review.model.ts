import { Moment } from 'moment';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysReview {
    id?: number;
    content?: any;
    level?: number;
    createTime?: Moment;
    updateTime?: Moment;
    product?: ISysProduct;
}

export class SysReview implements ISysReview {
    constructor(
        public id?: number,
        public content?: any,
        public level?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public product?: ISysProduct
    ) {}
}
