import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';

type EntityResponseType = HttpResponse<ISysShoppingCar>;
type EntityArrayResponseType = HttpResponse<ISysShoppingCar[]>;

@Injectable({ providedIn: 'root' })
export class SysShoppingCarService {
    public resourceUrl = SERVER_API_URL + 'api/sys-shopping-cars';

    constructor(protected http: HttpClient) {}

    create(sysShoppingCar: ISysShoppingCar): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysShoppingCar);
        return this.http
            .post<ISysShoppingCar>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysShoppingCar: ISysShoppingCar): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysShoppingCar);
        return this.http
            .put<ISysShoppingCar>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysShoppingCar>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysShoppingCar[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysShoppingCar: ISysShoppingCar): ISysShoppingCar {
        const copy: ISysShoppingCar = Object.assign({}, sysShoppingCar, {
            createTime:
                sysShoppingCar.createTime != null && sysShoppingCar.createTime.isValid() ? sysShoppingCar.createTime.toJSON() : null,
            updateTime: sysShoppingCar.updateTime != null && sysShoppingCar.updateTime.isValid() ? sysShoppingCar.updateTime.toJSON() : null
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
            res.body.forEach((sysShoppingCar: ISysShoppingCar) => {
                sysShoppingCar.createTime = sysShoppingCar.createTime != null ? moment(sysShoppingCar.createTime) : null;
                sysShoppingCar.updateTime = sysShoppingCar.updateTime != null ? moment(sysShoppingCar.updateTime) : null;
            });
        }
        return res;
    }
}
