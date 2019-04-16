/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysClassifyDetailComponent } from 'app/entities/sys-classify/sys-classify-detail.component';
import { SysClassify } from 'app/shared/model/sys-classify.model';

describe('Component Tests', () => {
    describe('SysClassify Management Detail Component', () => {
        let comp: SysClassifyDetailComponent;
        let fixture: ComponentFixture<SysClassifyDetailComponent>;
        const route = ({ data: of({ sysClassify: new SysClassify(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysClassifyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysClassifyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysClassifyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysClassify).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
