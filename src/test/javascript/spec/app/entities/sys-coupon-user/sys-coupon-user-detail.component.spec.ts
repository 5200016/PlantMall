/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponUserDetailComponent } from 'app/entities/sys-coupon-user/sys-coupon-user-detail.component';
import { SysCouponUser } from 'app/shared/model/sys-coupon-user.model';

describe('Component Tests', () => {
    describe('SysCouponUser Management Detail Component', () => {
        let comp: SysCouponUserDetailComponent;
        let fixture: ComponentFixture<SysCouponUserDetailComponent>;
        const route = ({ data: of({ sysCouponUser: new SysCouponUser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysCouponUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysCouponUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
