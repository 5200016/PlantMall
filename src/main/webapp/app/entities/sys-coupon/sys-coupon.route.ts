import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysCoupon } from 'app/shared/model/sys-coupon.model';
import { SysCouponService } from './sys-coupon.service';
import { SysCouponComponent } from './sys-coupon.component';
import { SysCouponDetailComponent } from './sys-coupon-detail.component';
import { SysCouponUpdateComponent } from './sys-coupon-update.component';
import { SysCouponDeletePopupComponent } from './sys-coupon-delete-dialog.component';
import { ISysCoupon } from 'app/shared/model/sys-coupon.model';

@Injectable({ providedIn: 'root' })
export class SysCouponResolve implements Resolve<ISysCoupon> {
    constructor(private service: SysCouponService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysCoupon> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysCoupon>) => response.ok),
                map((sysCoupon: HttpResponse<SysCoupon>) => sysCoupon.body)
            );
        }
        return of(new SysCoupon());
    }
}

export const sysCouponRoute: Routes = [
    {
        path: 'sys-coupon',
        component: SysCouponComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCoupon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon/:id/view',
        component: SysCouponDetailComponent,
        resolve: {
            sysCoupon: SysCouponResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCoupon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon/new',
        component: SysCouponUpdateComponent,
        resolve: {
            sysCoupon: SysCouponResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCoupon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon/:id/edit',
        component: SysCouponUpdateComponent,
        resolve: {
            sysCoupon: SysCouponResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCoupon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysCouponPopupRoute: Routes = [
    {
        path: 'sys-coupon/:id/delete',
        component: SysCouponDeletePopupComponent,
        resolve: {
            sysCoupon: SysCouponResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCoupon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
