import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysCouponUser } from 'app/shared/model/sys-coupon-user.model';
import { SysCouponUserService } from './sys-coupon-user.service';
import { SysCouponUserComponent } from './sys-coupon-user.component';
import { SysCouponUserDetailComponent } from './sys-coupon-user-detail.component';
import { SysCouponUserUpdateComponent } from './sys-coupon-user-update.component';
import { SysCouponUserDeletePopupComponent } from './sys-coupon-user-delete-dialog.component';
import { ISysCouponUser } from 'app/shared/model/sys-coupon-user.model';

@Injectable({ providedIn: 'root' })
export class SysCouponUserResolve implements Resolve<ISysCouponUser> {
    constructor(private service: SysCouponUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysCouponUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysCouponUser>) => response.ok),
                map((sysCouponUser: HttpResponse<SysCouponUser>) => sysCouponUser.body)
            );
        }
        return of(new SysCouponUser());
    }
}

export const sysCouponUserRoute: Routes = [
    {
        path: 'sys-coupon-user',
        component: SysCouponUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-user/:id/view',
        component: SysCouponUserDetailComponent,
        resolve: {
            sysCouponUser: SysCouponUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-user/new',
        component: SysCouponUserUpdateComponent,
        resolve: {
            sysCouponUser: SysCouponUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-user/:id/edit',
        component: SysCouponUserUpdateComponent,
        resolve: {
            sysCouponUser: SysCouponUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysCouponUserPopupRoute: Routes = [
    {
        path: 'sys-coupon-user/:id/delete',
        component: SysCouponUserDeletePopupComponent,
        resolve: {
            sysCouponUser: SysCouponUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
