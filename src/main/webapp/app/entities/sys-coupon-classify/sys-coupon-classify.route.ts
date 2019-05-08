import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';
import { SysCouponClassifyService } from './sys-coupon-classify.service';
import { SysCouponClassifyComponent } from './sys-coupon-classify.component';
import { SysCouponClassifyDetailComponent } from './sys-coupon-classify-detail.component';
import { SysCouponClassifyUpdateComponent } from './sys-coupon-classify-update.component';
import { SysCouponClassifyDeletePopupComponent } from './sys-coupon-classify-delete-dialog.component';
import { ISysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';

@Injectable({ providedIn: 'root' })
export class SysCouponClassifyResolve implements Resolve<ISysCouponClassify> {
    constructor(private service: SysCouponClassifyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysCouponClassify> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysCouponClassify>) => response.ok),
                map((sysCouponClassify: HttpResponse<SysCouponClassify>) => sysCouponClassify.body)
            );
        }
        return of(new SysCouponClassify());
    }
}

export const sysCouponClassifyRoute: Routes = [
    {
        path: 'sys-coupon-classify',
        component: SysCouponClassifyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-classify/:id/view',
        component: SysCouponClassifyDetailComponent,
        resolve: {
            sysCouponClassify: SysCouponClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-classify/new',
        component: SysCouponClassifyUpdateComponent,
        resolve: {
            sysCouponClassify: SysCouponClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-classify/:id/edit',
        component: SysCouponClassifyUpdateComponent,
        resolve: {
            sysCouponClassify: SysCouponClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponClassify.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysCouponClassifyPopupRoute: Routes = [
    {
        path: 'sys-coupon-classify/:id/delete',
        component: SysCouponClassifyDeletePopupComponent,
        resolve: {
            sysCouponClassify: SysCouponClassifyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponClassify.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
