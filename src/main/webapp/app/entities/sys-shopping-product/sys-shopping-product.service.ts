import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';

type EntityResponseType = HttpResponse<ISysShoppingProduct>;
type EntityArrayResponseType = HttpResponse<ISysShoppingProduct[]>;

@Injectable({ providedIn: 'root' })
export class SysShoppingProductService {
    public resourceUrl = SERVER_API_URL + 'api/sys-shopping-products';

    constructor(protected http: HttpClient) {}

    create(sysShoppingProduct: ISysShoppingProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysShoppingProduct);
        return this.http
            .post<ISysShoppingProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysShoppingProduct: ISysShoppingProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysShoppingProduct);
        return this.http
            .put<ISysShoppingProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysShoppingProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysShoppingProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysShoppingProduct: ISysShoppingProduct): ISysShoppingProduct {
        const copy: ISysShoppingProduct = Object.assign({}, sysShoppingProduct, {
            createTime:
                sysShoppingProduct.createTime != null && sysShoppingProduct.createTime.isValid()
                    ? sysShoppingProduct.createTime.toJSON()
                    : null,
            updateTime:
                sysShoppingProduct.updateTime != null && sysShoppingProduct.updateTime.isValid()
                    ? sysShoppingProduct.updateTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysShoppingProduct: ISysShoppingProduct) => {
                sysShoppingProduct.createTime = sysShoppingProduct.createTime != null ? moment(sysShoppingProduct.createTime) : null;
                sysShoppingProduct.updateTime = sysShoppingProduct.updateTime != null ? moment(sysShoppingProduct.updateTime) : null;
            });
        }
        return res;
    }
}
